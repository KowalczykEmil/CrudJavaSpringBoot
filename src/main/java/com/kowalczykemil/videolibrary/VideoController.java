package com.kowalczykemil.videolibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

    @GetMapping("/test")
    public int test() {
        return 1;
    }


    @GetMapping("")
        public List<Video> getAll() {
        return videoRepository.getAll();
        }

        @GetMapping("/{id}")
        public Video getById(@PathVariable("id") int id) {
        return videoRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List <Video> videos) {
        return videoRepository.save(videos);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Video updatedBody){
        Video video = videoRepository.getById(id);

        if(video != null) {
            video.setName(updatedBody.getName());
            video.setRating(updatedBody.getRating());

            videoRepository.update(video);

            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Video updatedBody) {
        Video video = videoRepository.getById(id);

        if(video != null) {
            if(updatedBody.getName() != null) { video.setName(updatedBody.getName());}
            if(updatedBody.getRating() > 0) {video.setRating(updatedBody.getRating());}

            videoRepository.update(video);

            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return videoRepository.delete(id);
    }
}
