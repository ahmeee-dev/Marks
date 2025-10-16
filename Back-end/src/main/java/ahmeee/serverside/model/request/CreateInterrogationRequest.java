package ahmeee.serverside.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

    public class CreateInterrogationRequest {

        @NotNull
        @JsonProperty("text_attributes")
        private TextAttributes textAttributes;

        @NotNull
        @JsonProperty("grade_attributes")
        private GradeAttributes gradeAttributes;

        @NotNull
        private Settings settings;

        public CreateInterrogationRequest() {}

        public TextAttributes getTextAttributes() {
            return textAttributes;
        }
        public void setTextAttributes(TextAttributes textAttributes) {
            this.textAttributes = textAttributes;
        }

        public static class TextAttributes {
            @NotBlank
            private String argument;
            private String synthesis;
            private String trimmed;
            @NotBlank
            private String content;

            public String getArgument() {
                return argument;
            }

            public void setArgument(String argument) {
                this.argument = argument;
            }

            public String getSynthesis() {
                return synthesis;
            }

            public void setSynthesis(String synthesis) {
                this.synthesis = synthesis;
            }

            public String getTrimmed() {
                return trimmed;
            }

            public void setTrimmed(String trimmed) {
                this.trimmed = trimmed;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public GradeAttributes getGradeAttributes() {
            return gradeAttributes;
        }

        public void setGradeAttributes(GradeAttributes gradeAttributes) {
            this.gradeAttributes = gradeAttributes;
        }

        public static class GradeAttributes {
            @Min(4)
            @Max(10)
            private Integer grade;
            @PositiveOrZero
            private Integer	quantity;

            public Integer getGrade() {
                return grade;
            }
            public void setGrade(Integer grade) {
                this.grade = grade;
            }

            public Integer getQuantity() {
                return quantity;
            }
            public void setQuantity(Integer quantity) {
                this.quantity = quantity;
            }
        }

        public Settings getSettings() {
            return settings;
        }
        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public static class Settings {
            @NotNull
            @Min(1)
            @Max(4)
            private Integer difficulty;

            public Settings() {}

            public Integer getDifficulty() {
                return difficulty;
            }
            public void setDifficulty(Integer difficulty) {
                this.difficulty = difficulty;
            }
        }

}