package ru.otus.service.impl

import org.springframework.stereotype.Service
import ru.otus.model.Action
import ru.otus.model.Event
import ru.otus.model.Task
import ru.otus.service.ActionService
import ru.otus.service.CalendarService
import ru.otus.service.EventService
import ru.otus.service.TaskService

import java.time.LocalDateTime

@Service
class CalendarServiceImpl implements CalendarService{

    private final ActionService actionService
    private final TaskService taskService
    private final EventService eventService

    CalendarServiceImpl(ActionService actionService,
                        TaskService taskService,
                        EventService eventService){
        this.actionService = actionService
        this.taskService = taskService
        this.eventService = eventService
    }

    @Override
    Boolean validate(Object entity, List entityList) {
        if(entity.getClass() == Task.class){
            def task = (Task) entity
            def tasks = (List<Task>) entityList
            validateTask(task, tasks)
        } else if (entity.getClass() == Action.class){
            Action action = (Action) entity
            def actions = (List<Action>) entityList
            validateAction(action, actions)
        }
        throw new RuntimeException('Невозможно проверить время добавляемой задачи или действия!')
    }

    @Override
    Task createNewTask(Task task) {
        List<Task> tasks = taskService.getAll()
        if (validateTask(task, tasks)){
            taskService.create(task)
        }
        throw new RuntimeException('Укажите другое время для этой задачи. Заданное время уже занято!')
    }

    @Override
    Action createNewAction(Action action) {
        List<Action> actions = actionService.getAll()
        Task existTask = taskService.get(action.getDateTimeStartAction())
        if (validateAction(action, actions) && (getTaskTimeLeft(existTask) >= action.getActionDuration())){
            Task currentTask = taskService.get(action.getDateTimeStartAction())
            Action addedAction = actionService.create(action)
            currentTask.getActionList().add(actionService.create(addedAction))
        }
        throw new RuntimeException('Укажите другое время для действия. Заданное время уже занято!')
    }

    @Override
    def deleteTask(Task task) {
        try{
            actionService.deleteAll(task.getActionList())
        } catch (Exception e){
            throw new RuntimeException('Не удалось удалить список действий для задачи : ' + task.getTaskName() + '. ' + e.getMessage())
        }
        try{
            taskService.delete(task)
        } catch (Exception e){
            throw new RuntimeException('Не удалось удалить задачу : ' + task.getTaskName() + '. ' + e.getMessage())
        }
    }

    @Override
    def deleteAction(Action action) {
        try{
            actionService.delete(action)
        } catch (Exception e){
            throw new RuntimeException('Не удалось удалить действие : ' + action.getActionName() + '. ' + e.getMessage())
        }
    }

    @Override
    def renameTask(Task task) {
        try{
            taskService.update(task)
        } catch (Exception e){
            throw new RuntimeException('Не удалось переименовать задачу : ' + task.getTaskName() + '. ' + e.getMessage())
        }
    }

    @Override
    def renameAction(Action action) {
        try{
            actionService.update(action)
        } catch (Exception e){
            throw new RuntimeException('Не удалось переименовать действие : ' + action.getActionName() + '. ' + e.getMessage())
        }
    }

    @Override
    Integer getCountTaskFromDate(LocalDateTime dateTime) {
        getCountTask(taskService.getAll(), dateTime)
    }

    @Override
    String getBusyTimeFromDate(LocalDateTime dateTime) {
        getDisplayTime(getTaskTime(getTasksFromDate(taskService.getAll(), dateTime)))
    }

    @Override
    List<Task> getAllTaskFromDate(LocalDateTime dateTime) {
        getTasksFromDate(taskService.getAll(), dateTime)
    }

    @Override
    def fireEvent(Task task, LocalDateTime startTime) {
        Event event = new Event()
        Action action = getAction(task, startTime)
        event.setEventName(getDisplayTime(task.getDateTimeStartTask()) + '\n' + task.getTaskName() + '\n' + getDisplayTime(task.getDateTimeEndTask()) + '\n' + getDisplayTime(action.getDateTimeStartAction()) + '\n' + action.getActionName() + '\n' + getDisplayTime(action.getDateTimeEndAction()))
        event.setDateTimeStart(startTime)
        eventService.fireEvent(event)
    }

    /** Test */
    static Boolean validateTask(Task task, List<Task> taskList){
        taskList.findAll() {
            it.getDateTimeStartTask() < task.getDateTimeStartTask()
            it.getDateTimeEndTask() > task.getDateTimeEndTask()
        }.collect().isEmpty()
    }

    /** Test */
    static Boolean validateAction(Action action, List<Action> actionList){
        actionList.findAll() {
            it.getDateTimeStartAction() < action.getDateTimeStartAction()
            it.getDateTimeEndAction() > action.getDateTimeEndAction()
        }.collect().isEmpty()
    }

    /** Test */
    static Integer getCountTask(List<Task> tasks, LocalDateTime dateTime){
        tasks.findAll({
            it.getDateTimeStartTask().getYear() == dateTime.getYear()
            it.getDateTimeStartTask().getMonthValue() == dateTime.getMonthValue()
            it.getDateTimeStartTask().getDayOfMonth() == dateTime.getDayOfMonth()
        }).size()
    }

    /** Test */
    static List<Task> getTasksFromDate(List<Task> tasks, LocalDateTime dateTime){
        tasks.findAll({
            it.getDateTimeStartTask().getYear() == dateTime.getYear()
            it.getDateTimeStartTask().getMonthValue() == dateTime.getMonthValue()
            it.getDateTimeStartTask().getDayOfMonth() == dateTime.getDayOfMonth()
        }).collect()
    }

    /** Test */
    static Long getTaskTimeLeft(Task task){
        Long sum = 0L
        task.getActionList().forEach({
            sum += it.getActionDuration()
        })
        task.getTaskDuration() - sum
    }

    /** Test */
    static Long getTaskTime(List<Task> tasks){
        Long sum = 0L
        tasks.forEach({ it ->
            sum += getActionTime(it)
        })
        sum
    }

    /** Test */
    static Long getActionTime(Task task){
        Long sum = 0L
        task.getActionList().forEach({
            sum += it.getActionDuration()
        })
        sum
    }

    /** Test */
    static String getDisplayTime(LocalDateTime dateTime){
        dateTime.getYear() + '.' +  dateTime.getMonthValue() + '.' + dateTime.getDayOfMonth() + ' ' + dateTime.getHour() + ':' + dateTime.getMinute()
    }

    Action getAction(Task task, LocalDateTime startTime){
        task.getActionList().stream()
                .find {
                    actionService.get(it.getDateTimeStartAction()).getDateTimeStartAction() == startTime
                } as Action
    }

    /** Test */
    static String getDisplayTime(Long secondCount){
        Long hours =  secondCount.intdiv(60 * 60)
        secondCount = secondCount - hours * 60 * 60
        Long minutes = secondCount.intdiv(60)
        secondCount = secondCount - minutes * 60
        hours + ':' + minutes + ':' + secondCount
    }
}
