package com.fortuneloan.tenmileslotus.bean;

import java.util.List;

public class HelpJsonBean {

    /**
     * code : 200
     * help : [{"id":10,"problem":"Customer service opening hours?","answer":"Mon-Fri ：10AM to 5PM","create_time":null,"update_time":null},{"id":11,"problem":"Can\u2019t receive the SMS verification code?","answer":"Please contact our customer service.","create_time":null,"update_time":null},{"id":13,"problem":"Pay success, but I still could not get the cash.","answer":"The loan will be processed and credited to your account around 2-7 working days. Please be patient.","create_time":null,"update_time":null}]
     */

    private String code;
    private List<HelpBean> help;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<HelpBean> getHelp() {
        return help;
    }

    public void setHelp(List<HelpBean> help) {
        this.help = help;
    }

    public static class HelpBean {
        /**
         * id : 10
         * problem : Customer service opening hours?
         * answer : Mon-Fri ：10AM to 5PM
         * create_time : null
         * update_time : null
         */

        private int id;
        private String problem;
        private String answer;
        private Object create_time;
        private Object update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProblem() {
            return problem;
        }

        public void setProblem(String problem) {
            this.problem = problem;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public Object getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(Object update_time) {
            this.update_time = update_time;
        }
    }
}
