package com.esgi.jeeproject.vibecritical.domain.entities.WarningWord;

import javax.persistence.*;

@Entity
@Table(name = "warning_word")
public class WarningWord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "word")
    private String word;

    @Column(name = "warning_level")
    private int warningLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(int warningLevel) {
        this.warningLevel = warningLevel;
    }
}
