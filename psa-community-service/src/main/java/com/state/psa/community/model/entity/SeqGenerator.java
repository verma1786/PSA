package com.state.psa.community.model.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class SeqGenerator {
    @Id
    @SequenceGenerator(name = "fileSeqGen", sequenceName = "t_file_seq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "fileSeqGen")
    private int fileId;
}
