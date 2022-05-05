package com.state.psa.lost.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name="t_lost_article")
public class LostArticle implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LOST_ARTICLE_ID" ,length=20 ,nullable = false,unique = true)
    private Long articleId;

    @Column(name="LOST_ARTICLE_TYPE_ID" ,length=11 ,nullable = false)
    private Integer lostArticleTypeId;
    @Column(name="LOST_ARTICLE" ,length=20 ,nullable = false)
    private String lostArticle;
    @Column(name="LOST_DESCRIPTION" ,length=500,nullable = false)
    private String description;

//    @Column(name="COMPLAINANT_ID" ,length=20 ,nullable = false)
//    private Long complainantId;
//    @ManyToOne
//    @JoinColumn(name="COMPLAINANT_ID", nullable=false)
//    private ComplainantDetail complainantDetail;


}
