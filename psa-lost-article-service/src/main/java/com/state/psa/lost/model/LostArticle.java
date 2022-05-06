package com.state.psa.lost.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "T_PSA_SEQUENCE")
    @TableGenerator(name="T_PSA_SEQUENCE", table="T_PSA_SEQUENCE", schema="psa_state_lost_db",allocationSize = 1)
    private Long articleId;

    @Column(name="LOST_ARTICLE_TYPE_ID" ,length=11 ,nullable = false)
    private Integer lostArticleTypeId;
    @Column(name="LOST_ARTICLE" ,length=20 ,nullable = false)
    private String lostArticle;
    @Column(name="LOST_DESCRIPTION" ,length=500,nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPLAINANT_ID", referencedColumnName = "complainantId")
    @JsonIgnoreProperties("articles")
    private ComplainantDetail complainantDetail;




}
