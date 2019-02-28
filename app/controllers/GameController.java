package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import scala.Int;

import javax.inject.Inject;
import java.util.Random;

public class GameController extends Controller
{
    private FormFactory formFactory;
    final private String CHARACTER_NAME_SESSION_KEY = "CHARACTER_NAME";
    final private String CREW_SESSION_KEY = "CREW";
    private String[] crew = new String[10];


    @Inject
    public GameController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
        crew[0] = "Bob";
        crew[1] = "John";
        crew[2] = "Lisa";
        crew[3] = "Steve";
        crew[4] = "Mary";
        crew[5] = "Barbara";
        crew[6] = "Joe";
        crew[7] = "Ben";
        crew[8] = "Larry";
        crew[9] = "Tom";
    }

    public Result getWelcome()
    {
        return ok(views.html.welcome.render());
    }

    public Result postStart(Http.Request request)
    {
        DynamicForm form = formFactory.form().bindFromRequest(request);
        String characterName = form.get("charactername");
        int startingCrew = 10;

        return ok(views.html.start.render(String.valueOf(startingCrew), crewNames(request, crew))).addingToSession(request, CHARACTER_NAME_SESSION_KEY, characterName)
                .addingToSession(request, CREW_SESSION_KEY, String.valueOf(startingCrew));
    }

    public Result postEastFromEngland(Http.Request request)
    {
        String currentCrew = request.session().getOptional(CREW_SESSION_KEY).orElse("0");
        String lostMember = crew[Integer.parseInt(request.session().getOptional(CREW_SESSION_KEY).orElse("0")) - 1];
        String newCrew = changeCrew(currentCrew);

        if (Integer.parseInt(newCrew) >= 5)
        {
            return ok(views.html.eastfromengland.render(newCrew, lostMember, crewNames(request, crew))).addingToSession(request, CREW_SESSION_KEY, newCrew);
        }
        else
        {
            return ok(views.html.oakisland.render(currentCrew, crewNames(request, crew)));
        }
    }

    public Result postNorthFromEngland(Http.Request request)
    {
        String currentCrew = request.session().getOptional(CREW_SESSION_KEY).orElse("0");
        java.util.Random random = new java.util.Random();
        int chance = random.nextInt(4);

        if(chance == 0)
        {
            return ok(views.html.santaend.render());
        }
        else if(Integer.parseInt(currentCrew) == 5)
        {
            return ok(views.html.narnia.render());
        }
        else
        {
            return ok(views.html.northfromengland.render(currentCrew));
        }
    }

    public Result postNorthEnd(Http.Request request)
    {
        String currentCrew = request.session().getOptional(CREW_SESSION_KEY).orElse("0");

        return ok(views.html.northend.render(request.session().getOptional(CHARACTER_NAME_SESSION_KEY)
                .orElse("Unknown"), currentCrew));
    }

    public Result postWestFromEngland(Http.Request request)
    {
        String currentCrew = request.session().getOptional(CREW_SESSION_KEY).orElse("0");
        String lostMember = crew[Integer.parseInt(request.session().getOptional(CREW_SESSION_KEY).orElse("0")) - 1];
        String newCrew = changeCrew(currentCrew);

        if (Integer.parseInt(newCrew) >= 5)
        {
            return ok(views.html.westfromengland.render(newCrew, lostMember, crewNames(request, crew))).addingToSession(request, CREW_SESSION_KEY, newCrew);
        }
        else
        {
            return ok(views.html.oakisland.render(currentCrew, crewNames(request, crew)));
        }
    }

    public Result postEastEnd(Http.Request request)
    {
        String currentCrew = request.session().getOptional(CREW_SESSION_KEY).orElse("0");

        return ok(views.html.eastend.render(request.session().getOptional(CHARACTER_NAME_SESSION_KEY).orElse("Unknown"), currentCrew, crewNames(request, crew)));
    }

    public Result postWestEnd(Http.Request request)
    {
        String currentCrew = request.session().getOptional(CREW_SESSION_KEY).orElse("0");

        return ok(views.html.westend.render(request.session().getOptional(CHARACTER_NAME_SESSION_KEY).orElse("Unknown"), currentCrew));
    }

    public Result postHomePort(Http.Request request)
    {
        String currentCrew = request.session().getOptional(CREW_SESSION_KEY).orElse("0");

        return ok(views.html.homeport.render(currentCrew, crewNames(request, crew)));
    }


    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }


    public Result getStart(Http.Request request)
    {
        String crewMembers = "10";

        return ok(views.html.start.render(crewMembers, crewNames(request, crew)));
    }

    private String changeCrew(String currentCrew)
    {
        int newCrew = Integer.parseInt(currentCrew) - 1;
        return String.valueOf(newCrew);
    }

    private String crewNames(Http.Request request, String[] crewMembers)
    {
        String currentCrew = request.session().getOptional(CREW_SESSION_KEY).orElse("0");
        String crewNames = "";

        for(int i = 0; i < Integer.parseInt(currentCrew) - 1; i++)
        {
            if(i < Integer.parseInt(currentCrew) - 2)
            {
                crewNames = crewNames + crewMembers[i] + ", ";
            }
            else
            {
                crewNames = crewNames + "and " + crewMembers[i];
            }
        }
        return crewNames;
    }

}
