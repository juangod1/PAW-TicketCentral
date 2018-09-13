package ar.edu.itba.paw2018b.webapp.controller;

import ar.edu.itba.paw2018b.interfaces.service.ScreeningService;
import ar.edu.itba.paw2018b.interfaces.service.TheatreService;
import ar.edu.itba.paw2018b.models.Theatre;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Controller
public class TheatreController {
    @Autowired
    TheatreService theatreService;
    @Autowired
    ScreeningService screeningService;

    @RequestMapping(value = "/json/theatre/getTheatres", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<Theatre>> getTheatres()
    {
        List<Theatre> list = theatreService.getTheatres();
        if(list.size()==0)
        {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    /**
     *
     * @param screeningJson Expects a JSON string of the following format
     *                      "[
     *                          Id screening 1,
     *                          ...
     *                          Id screening n
     *                       ]
     *                      "
     * @return
     * @throws JsonParseException If string is not formatted correctly
     * @throws IOException If string is not formatted correctly
     */
    @RequestMapping(value = "/json/theatre/getTheatresByScreenings", method = RequestMethod.POST, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity<List<String>> getTheatresByScreenings(@RequestBody String screeningJson) throws JsonParseException, IOException
    {
        List<Integer> screenings = new ObjectMapper().readValue(screeningJson, new TypeReference<List<Integer>>() { });

        List<String> list = screeningService.getTheatresByScreening(screenings);
        if(list.size()==0)
        {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
