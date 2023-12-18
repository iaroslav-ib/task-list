package controllers

import javax.inject._
import models.Task
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class TaskController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index: Action[AnyContent] = Action {Redirect(routes.TaskController.tasks)}

  def tasks: Action[AnyContent] = Action {
    Ok(views.html.index(Task.all))
  }

  def newTask = Action(parse.formUrlEncoded) {
    implicit request =>
      Task.add(request.body("taskName").head)
      Redirect(routes.TaskController.index)
  }

  def deleteTask(id: Int) = Action {
    Task.delete(id)
    Ok
  }
}
