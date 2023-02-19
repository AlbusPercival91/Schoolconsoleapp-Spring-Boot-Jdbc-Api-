package ua.foxminded.springbootjdbc.school.movie;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


//    @Override
//    public int insertMovie(Movie movie) {
//        var sql = """
//                INSERT INTO movie(name)
//                VALUES (?);
//                 """;
//        return jdbcTemplate.update(
//                sql,
//                movie.name()
//        );
//    }
//
//    @Override
//    public int deleteMovie(int id) {
//        var sql = """
//                DELETE FROM movie   
//                WHERE id = ?
//                """;
//        return jdbcTemplate.update(sql, id);
//    }
//
//    @Override
//    public Optional<Movie> selectMovieById(int id) {
//        var sql = """
//                SELECT id, name, release_date
//                FROM movie
//                WHERE id = ?
//                 """;
//        return jdbcTemplate.query(sql, new StudentRowMapper(), id)
//                .stream()
//                .findFirst();
//    }

}
