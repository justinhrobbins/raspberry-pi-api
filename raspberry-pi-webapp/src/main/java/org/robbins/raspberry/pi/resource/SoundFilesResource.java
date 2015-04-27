
package org.robbins.raspberry.pi.resource;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.SoundFiles;
import org.robbins.raspberry.pi.service.PlaySoundActionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("/sound-files")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class SoundFilesResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoundFilesResource.class);

    @Autowired
    private PlaySoundActionImpl soundService;

    @GET
    public SoundFiles getFilesList() throws RaspberryPiAppException {
        LOGGER.debug("Received request to GET sound files");

        return soundService.getSoundFiles();
    }
}
