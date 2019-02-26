package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class GameController extends Controller
{
    public Result getWelcome()
    {
        return ok(views.html.welcome.render());
    }

    public Result postStart()
    {
        return ok(views.html.start.render());
    }

    public Result postEastFromEngland()
    {
        return ok(views.html.eastfromengland.render());
    }

    public Result postNorthEnd()
    {
        return ok(views.html.northend.render());
    }

    public Result postWestFromEngland()
    {
        return ok(views.html.westfromengland.render());
    }

    public Result postEastEnd()
    {
        return ok(views.html.eastend.render());
    }

    public Result postWestEnd()
    {
        return ok(views.html.westend.render());
    }

    public Result postHomePort()
    {
        return ok(views.html.homeport.render());
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }
}
