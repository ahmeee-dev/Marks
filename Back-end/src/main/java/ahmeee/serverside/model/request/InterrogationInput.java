package ahmeee.serverside.model.request;

import java.util.ArrayList;

public class InterrogationInput {
    private String argument;
    private Settings settings;
    private Last last;
    private ArrayList<PreviousList> previousList;

    public InterrogationInput() {}

    public class Settings {
        private Integer difficulty;
        private boolean expositionJudgement;

        public Settings() {}

        public Integer getDifficulty() {
            return difficulty;
        }
        public void setDifficulty(Integer difficulty) {
            this.difficulty = difficulty;
        }
        public boolean isExpositionJudgement() {
            return expositionJudgement;
        }
        public void setExpositionJudgement(boolean expositionJudgement) {
            this.expositionJudgement = expositionJudgement;
        }
    }

    public class Last {
        private String question;
        private String answer;

        public Last() {}

        public String getQuestion() {
            return question;
        }
        public void setQuestion(String question) {
            this.question = question;
        }
        public String getAnswer() {
            return answer;
        }
        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }

    public class PreviousList {
        private String question;
        private Integer grade;
            
        public PreviousList() {}
        public String getQuestion() {
            return question;
        }
        public void setQuestion(String question) {
            this.question = question;
        }
        public Integer getGrade() {
            return grade;
        }
        public void setGrade(Integer grade) {
            this.grade = grade;
        }
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Last getLast() {
        return last;
    }

    public void setLast(Last last) {
        this.last = last;
    }

    public ArrayList<PreviousList> getPreviousList() {
        return previousList;
    }

    public void setPreviousList(ArrayList<PreviousList> previousList) {
        this.previousList = previousList;
    }

}