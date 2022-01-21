package shorten.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shorten.url.model.Url;

import java.util.Optional;


public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByLongUrl(String longUrl);

}

