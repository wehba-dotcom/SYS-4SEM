package entities;

import dtos.GoalDTO;

import javax.persistence.*;

@Entity
@Table(name = "goal")
@NamedQuery(name = "goal.deleteAllRows", query = "DELETE from Goal")
public class Goal
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Goal_SEQ")
    @SequenceGenerator(name = "Goal_SEQ")
    @Column(name = "id", nullable = false)
    private int id;
    private int answersWrong;
    private String finishDate;

    public Goal()
    {
    }

    public Goal(GoalDTO goalDTO)
    {
        this.answersWrong = goalDTO.getDto_answersWrong();
        this.finishDate = goalDTO.getDto_finishDate();
    }

    public Goal(int answersWrong, String finishDate)
    {
        this.answersWrong = answersWrong;
        this.finishDate = finishDate;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAnswersWrong()
    {
        return answersWrong;
    }

    public void setAnswersWrong(int answersWrong)
    {
        this.answersWrong = answersWrong;
    }

    public String getFinishDate()
    {
        return finishDate;
    }

    public void setFinishDate(String finishDate)
    {
        this.finishDate = finishDate;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }


}