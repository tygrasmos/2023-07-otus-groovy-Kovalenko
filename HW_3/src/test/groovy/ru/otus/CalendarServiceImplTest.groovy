package ru.otus

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.otus.model.Action
import ru.otus.model.Task
import ru.otus.service.impl.CalendarServiceImpl

import java.time.Duration
import java.time.LocalDateTime

class CalendarServiceImplTest {

    private final static Integer YEAR = 2023
    private final static Integer MONTH = 10
    private final static Integer DAY = 5
    private final static Integer HOUR_1 = 9
    private final static Integer HOUR_2 = 10
    private final static Integer HOUR_3 = 11
    private final static Integer MINUTE_1 = 30
    private final static Integer MINUTE_2 = 0
    private final static Integer MINUTE_3 = 45
    private final static String DATE_TIME = '2023.10.5 9:30'

    @Autowired
    private CalendarServiceImpl calendarService

    @Test
    void validationOfTheTimePeriodTaskMustBeCorrect(){
        Task testTask = getTask('Работа', 'Проверка почты', LocalDateTime.of(YEAR, MONTH, DAY, HOUR_3, MINUTE_2),
                LocalDateTime.of(YEAR, MONTH, DAY, HOUR_3, MINUTE_1), new ArrayList<>())
        List<Task> existTasks = getTasks()
        assert CalendarServiceImpl.validateTask(testTask, existTasks)
    }

    @Test
    void validationOfTheTimePeriodActionMustBeCorrect(){
        Action testAction = getAction()
        List<Action> existActions = getActions(1)
        assert CalendarServiceImpl.validateAction(testAction, existActions)
    }

    @Test
    void TimeLeftMustBeGreaterThanActionDurationTime(){
        assert CalendarServiceImpl.getTaskTimeLeft(getTask('Здоровье', 'Зарядка', LocalDateTime.of(YEAR, MONTH, DAY, HOUR_1, MINUTE_1),
                LocalDateTime.of(YEAR, MONTH, DAY, HOUR_2, MINUTE_2), getActions(1))) >= 15 * 60
    }

    @Test
    void printDateTimeMustBeCorrect(){
        assert CalendarServiceImpl.getDisplayTime(LocalDateTime.of(YEAR, MONTH, DAY, HOUR_1, MINUTE_1)) == DATE_TIME
    }

    @Test
    void countTaskFromDateMustBeCorrect(){
        assert CalendarServiceImpl.getCountTask(getTasks(), LocalDateTime.of(YEAR, MONTH, DAY, HOUR_1, MINUTE_1)) == 2
    }

    @Test
    void busyTimeFromDateMustBeCorrect(){
        assert CalendarServiceImpl.getTaskTime(getTasks()) == 45 * 60
    }

    @Test
    void tasksFromDateMustBeCorrect(){
        assert CalendarServiceImpl.getTasksFromDate(getTasks(), LocalDateTime.of(YEAR, MONTH, DAY, HOUR_1, MINUTE_1)).size() == 2
    }

    @Test
    void timeDisplayMustBeCorrect(){
        assert CalendarServiceImpl.getDisplayTime(9000) == '2:30:0'
    }

    List<Task> getTasks(){
        List<Task> tasks = new ArrayList<>()
        tasks.add(getTask('Здоровье', 'Зарядка', LocalDateTime.of(YEAR, MONTH, DAY, HOUR_1, MINUTE_1),
                LocalDateTime.of(YEAR, MONTH, DAY, HOUR_2, MINUTE_2), getActions(1)))
        tasks.add(getTask('Здоровье', 'Пробежка',
                LocalDateTime.of(YEAR, MONTH, DAY, HOUR_2, MINUTE_2),
                LocalDateTime.of(YEAR, MONTH, DAY, HOUR_2, MINUTE_1), getActions(2)))
        tasks
    }

    Task getTask(String description, String name, LocalDateTime start, LocalDateTime end, List<Action> actions){
        Task task = new Task();
        task.setActionList(actions)
        task.setTaskName(name)
        task.setDateTimeStartTask(start)
        task.setDateTimeEndTask(end)
        task.setTaskDuration(getDuration(start, end))
        task.setTaskDescription(description)
        task
    }

    List<Action> getActions(Integer taskNumber) {
        List<Action> actions = new ArrayList<>()
        if (taskNumber == 1) {
            actions.add(getAction('Разминка', LocalDateTime.of(YEAR, MONTH, DAY, HOUR_1, MINUTE_1), LocalDateTime.of(YEAR, MONTH, DAY, HOUR_1, MINUTE_3)))
        } else if (taskNumber == 2){
            actions.add(getAction('Пробежка трусцой', LocalDateTime.of(YEAR, MONTH, DAY, HOUR_2, MINUTE_2), LocalDateTime.of(YEAR, MONTH, DAY, HOUR_2, MINUTE_1)))
        }
        actions
    }

    Action getAction(String name, LocalDateTime start, LocalDateTime end){
        Action action = new Action()
        action.setActionName(name)
        action.setActionDuration(getDuration(start, end))
        action.setDateTimeStartAction(start)
        action.setDateTimeEndAction(end)
        action
    }

    long getDuration(LocalDateTime start, LocalDateTime end){
        Duration duration = Duration.between(start, end)
        duration.getSeconds()
    }



}
