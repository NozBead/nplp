package funetdelire.oublierparoles.resources;

import java.util.List;

import funetdelire.oublierparoles.database.SongsData;
import funetdelire.oublierparoles.utils.RandomItemUtils;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/difficulties")
public class DifficultyResource {
	@Context
	private UriInfo currentUri;
	
	@Path("{difficulty}/random")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRandomThemeOfDifficulty(@PathParam("difficulty") int difficulty, List<Integer> excludeList) {
		List<Integer> themes = SongsData.getInstance().getThemeByDifficulty(difficulty);
		return RandomItemUtils.randomItemResponse(currentUri.getBaseUri(), themes, excludeList, s -> s,
				ThemeResource.class.getAnnotation(Path.class).value());
	}
}
