package com.mudassirkhan.data.remote

import com.mudassirkhan.data.Constants.ENGINEER_OFF_DAYS
import com.mudassirkhan.data.Constants.MAX_SHIFTS_PER_DAY
import com.mudassirkhan.data.Constants.MAX_SHIFTS_PER_ENGINEER
import com.mudassirkhan.data.Constants.SCHEDULE_PERIOD_DAYS
import com.mudassirkhan.domain.entity.Engineer
import com.mudassirkhan.domain.entity.Schedule
import java.util.*

class ScheduleService (engineers: ArrayList<Engineer>?) {


    // The list of engineers.
    private var engineers: ArrayList<Engineer>? = null

    // A copy of engineers list.
    private var copyOfEngineers: HashMap<String, Engineer> = LinkedHashMap()

    // engineers with how many shifts they have
    private var shifts: HashMap<Int, List<Engineer>> = LinkedHashMap()

    // Track of total shifts for each engineer.
    private val engineerTotalShifts = LinkedHashMap<String, Int>()

    // Rule 2: An engineer can do at most one shift in a day.
    // MAX_SHIFTS_PER_DAY
    // Rule 3: An engineer cannot have more than one shift on any consecutive days
    // ENGINEER_OFF_DAYS
    private var engineerResting: ArrayList<Engineer> = ArrayList()

    // Rule 4: Each engineer should have completed 2 shifts of support in any 2 week period.
    // MAX_SHIFTS_PER_ENGINEER

    // Invalid engineers who are not eligible to inter the pool.
    private var removedEngineers: ArrayList<String> = ArrayList()
    private var takenShits: Int = 0


    /**
     * Get a list of schedules.
     *
     * @return a list of schedules.
     * @throws ScheduleException if there is an engineer did not take MAX_SHIFTS_PER_ENGINEER.
     */
    // loop through all days to create shifts for each day.
    // create shifts for the current day.
    // add the  generated schedule into schedules.
    // for rule 4 making sure that no engineer left with less than MAX_SHIFTS_PER_ENGINEER shifts.
    val schedules: List<Schedule>?
        get() {
            if (engineers == null || engineers!!.size == 0) {
                return null
            }
            val schedules = ArrayList<Schedule>()
            for (i in 0 until SCHEDULE_PERIOD_DAYS) {
                val schedule = generateShifts(i)
                schedules.add(schedule)

            }
            if (removedEngineers.size != copyOfEngineers.size) {
                throw ScheduleException(ScheduleException.SCHEDULE_GENERAL_EXCEPTION)
            }
            return schedules
        }

    /**
     * Get a random engineer.
     *
     * @return A random engineer.
     * @throws ScheduleException if engineers size is less that 1.
     */

    // Making sure that after takenShits >= totalShifts / MAX_SHIFTS_PER_DAY, find each engineer
    // who dose not have any shift assigned already and then randomly select one.
    // find each engineer who dose not have 1 to MAX_SHIFTS_PER_DAY shifts assigned already and then randomly select one.
    // if there are no engineers have priority, add all the engineers to the pool.
    // randomly get an engineer.
    private fun getRandomEngineer(): Engineer {
        if (engineers != null) {
            if (engineers!!.size > 0) {

                val possibleCandidates = ArrayList<Engineer>()
                val totalShifts = SCHEDULE_PERIOD_DAYS * MAX_SHIFTS_PER_DAY
                if (takenShits >= totalShifts / MAX_SHIFTS_PER_DAY * ENGINEER_OFF_DAYS) {
                    for (i in 0 until MAX_SHIFTS_PER_DAY) {
                        if (possibleCandidates.size == 0) {
                            val engineers = shifts[i]
                            if (engineers != null && engineers.isNotEmpty()) {
                                possibleCandidates.addAll(engineers)
                            }
                            possibleCandidates.removeAll(engineerResting)
                        }

                    }

                }
                if (possibleCandidates.size == 0) {
                    possibleCandidates.addAll(engineers!!)
                }
                val randomIndex = getRandomNumber(possibleCandidates.size)
                return possibleCandidates[randomIndex]
            }
        }
        throw ScheduleException(ScheduleException.SCHEDULE_INVALID_EXCEPTION)
    }


    init {
        if (engineers != null) {
            this.engineers = engineers
            copyOfEngineers.clear()
            for (engineer in engineers) {
                copyOfEngineers[engineer.id!!.toString()] = engineer
                engineerTotalShifts[engineer.id!!.toString()] = 0
            }
            shifts[0] = ArrayList(engineers)

        }


    }

    /**
     * Generate shifts for the current day
     * Apply rule 1: There are only two support shifts per day, a day shift and a night shift.
     * Apply rule 2: Each engineer should have completed shifts in the given period.
     *
     * @param currentDay the current day.
     * @return shifts for the current day.
     */

    private fun generateShifts(currentDay: Int): Schedule {
        val shiftEngineers = ArrayList<Engineer>()

        // Rule 1: There are only two support shifts per day, a day shift and a night shift.
        for (j in 0 until MAX_SHIFTS_PER_DAY) {

            val pickedEngineer = getRandomEngineer()
            val engTotalShifts = engineerTotalShifts[pickedEngineer.id!!.toString()]!!
            var engineersWithShifts: ArrayList<Engineer>? =
                shifts[engTotalShifts] as ArrayList<Engineer>?

            if (engineersWithShifts != null) {

                engineersWithShifts.remove(pickedEngineer)
                shifts[engTotalShifts] = engineersWithShifts
            }

            val engNewTotalShifts = engTotalShifts + 1
            engineerTotalShifts[pickedEngineer.id!!.toString()] = engNewTotalShifts

            engineersWithShifts = shifts[engNewTotalShifts] as ArrayList<Engineer>?
            if (engineersWithShifts == null) {
                engineersWithShifts = ArrayList()
            }

            engineersWithShifts.add(pickedEngineer)
            shifts[engNewTotalShifts] = engineersWithShifts


            shiftEngineers.add(pickedEngineer)
            takenShits++

            // remove engineer so that the engineer will not enter the pool.
            this.engineers!!.remove(pickedEngineer)
            //  Rule 2: no more that one shift per day
            //  Rule 3: engineer off days.
            engineerResting.add(pickedEngineer)


            // Rule 4: Each engineer should have completed shifts in the given period.
            engineerCompletedMaxShifts(pickedEngineer)


            // Rule 2: new day so add back engineers who had been removed from the pool to avoid one engineer having more than one shift.
            // Rule 3: new day add back engineers and the off days.
            engineersResting(currentDay, j)
        }


        return Schedule(currentDay.toString(), currentDay, shiftEngineers)
    }

    /**
     * Add back the removed engineers after the off days.
     * Rule 3: An engineer cannot have more than one shift on any ENGINEER_OFF_DAYS days.
     * Rule 2: new day so add back engineers who had been removed from the pool to avoid one engineer having more than one shift.
     *
     * @param currentDay   the current day.
     * @param currentShift the current shift in the current day.
     */
    private fun engineersResting(currentDay: Int, currentShift: Int) {

        if (currentDay > ENGINEER_OFF_DAYS && currentShift == MAX_SHIFTS_PER_DAY - 1 || engineerResting.size == MAX_SHIFTS_PER_DAY * (ENGINEER_OFF_DAYS + 1)) {
            for (j in 0 until MAX_SHIFTS_PER_DAY) {
                if (engineerResting.size > 0) {
                    val engineer = engineerResting[0]
                    if (!engineers!!.contains(engineer) && !removedEngineers.contains(engineer.id!!.toString())) {
                        engineers!!.add(engineer)

                    }

                    engineerResting.removeAt(0)
                }
            }

        }
    }


    /**
     * Rule 4: Each engineer should have completed MAX_SHIFTS_PER_ENGINEER shifts of support in any SCHEDULE_PERIOD_DAYS week period.
     *
     * @param engineer the given engineer.
     */
    private fun engineerCompletedMaxShifts(engineer: Engineer) {
        val engTotalShifts = engineerTotalShifts[engineer.id!!.toString()]!!
        // Rule: Each engineer should have completed shifts in the given period.
        if (engTotalShifts == MAX_SHIFTS_PER_ENGINEER) {
            removedEngineers.add(engineer.id!!.toString())
        }
    }


    private fun getRandomNumber(max: Int): Int {
        val ran = Random()
        return ran.nextInt(max)
    }

}