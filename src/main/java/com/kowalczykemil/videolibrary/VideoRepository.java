package com.kowalczykemil.videolibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.http.HttpHeaders.FROM;

@Repository
public class VideoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Video> getAll() {
        List<Video> query = jdbcTemplate.query("SELECT * FROM video", BeanPropertyRowMapper.newInstance(Video.class));
        return query;
    }

    public Video getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, rating FROM video WHERE " + "id = ?", BeanPropertyRowMapper.newInstance(Video.class), id);
    }

    public int save(List <Video> videos) {
        videos.forEach(video -> jdbcTemplate
                .update("INSERT INTO video(name, rating) VALUES (?,?)",
                        video.getName(), video.getRating()
                ));

        return 1;
    }

    public int update(Video video) {
        return jdbcTemplate.update("UPDATE video SET name=?,rating=? WHERE id=?",
                video.getName(), video.getRating(), video.getId());
    }

    public int delete (int id) {
        return jdbcTemplate.update("DELETE FROM video where id=?", id);
    }
}
