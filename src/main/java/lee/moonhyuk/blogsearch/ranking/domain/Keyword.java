package lee.moonhyuk.blogsearch.ranking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Keyword {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String keyword;
    private Long hit;

    public Keyword() {
    }

    public Keyword(String keyword) {
        this.keyword = keyword;
        this.hit = 1L;
    }

    public Keyword increaseHit() {
        this.hit++;
        return this;
    }

    public Long getHit() {
        return hit;
    }
}
