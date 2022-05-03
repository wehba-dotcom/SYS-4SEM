package dtos;

import entities.Goal;

public class GoalDTO
{

    private int dto_goalID;
    private int dto_answersWrong;
    private String dto_finishDate;

    public GoalDTO()
    {
    }

    public GoalDTO(int dto_goalID, int dto_answersWrong, String dto_finishDate)
    {
        this.dto_goalID = dto_goalID;
        this.dto_answersWrong = dto_answersWrong;
        this.dto_finishDate = dto_finishDate;
    }

    public GoalDTO(int dto_answersWrong, String dto_finishDate)
    {
        this.dto_answersWrong = dto_answersWrong;
        this.dto_finishDate = dto_finishDate;
    }

    public GoalDTO(Goal g)
    {
        this.dto_goalID = g.getId();
        this.dto_answersWrong = g.getAnswersWrong();
        this.dto_finishDate = g.getFinishDate();
    }

    public int getDto_goalID()
    {
        return dto_goalID;
    }

    public void setDto_goalID(int dto_goalID)
    {
        this.dto_goalID = dto_goalID;
    }

    public int getDto_answersWrong()
    {
        return dto_answersWrong;
    }

    public void setDto_answersWrong(int dto_answersWrong)
    {
        this.dto_answersWrong = dto_answersWrong;
    }

    public String getDto_finishDate()
    {
        return dto_finishDate;
    }

    public void setDto_finishDate(String dto_finishDate)
    {
        this.dto_finishDate = dto_finishDate;
    }
}
