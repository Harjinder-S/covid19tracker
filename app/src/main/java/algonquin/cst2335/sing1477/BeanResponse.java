package algonquin.cst2335.sing1477;

import java.util.List;

public class BeanResponse {

    @Override
    public String toString() {
        return "BeanResponse{" +
                "province='" + province + '\'' +
                ", last_updated='" + last_updated + '\'' +
                ", data=" + data +
                '}';
    }

    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    private String last_updated;

    private List<Data> data;

    public static class Data {

        private String Id;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        private String date;

        private int change_cases;

        private int change_fatalities;

        private int change_tests;

        private int change_hospitalizations;

        private int change_criticals;

        private int change_recoveries;

        private int change_vaccinations;

        private int change_vaccinated;

        private int change_boosters_1;

        private int change_vaccines_distributed;

        private int total_cases;

        private int total_fatalities;

        private int total_tests;

        private int total_hospitalizations;

        private int total_criticals;

        private int total_recoveries;

        private int total_vaccinations;

        private int total_vaccinated;

        private int total_boosters_1;

        private int total_vaccines_distributed;

        public void setDate(String date) {
            this.date = date;
        }

        public String getDate() {
            return this.date;
        }

        public void setChange_cases(int change_cases) {
            this.change_cases = change_cases;
        }

        public int getChange_cases() {
            return this.change_cases;
        }

        public void setChange_fatalities(int change_fatalities) {
            this.change_fatalities = change_fatalities;
        }

        public int getChange_fatalities() {
            return this.change_fatalities;
        }

        public void setChange_tests(int change_tests) {
            this.change_tests = change_tests;
        }

        public int getChange_tests() {
            return this.change_tests;
        }

        public void setChange_hospitalizations(int change_hospitalizations) {
            this.change_hospitalizations = change_hospitalizations;
        }

        public int getChange_hospitalizations() {
            return this.change_hospitalizations;
        }

        public void setChange_criticals(int change_criticals) {
            this.change_criticals = change_criticals;
        }

        public int getChange_criticals() {
            return this.change_criticals;
        }

        public void setChange_recoveries(int change_recoveries) {
            this.change_recoveries = change_recoveries;
        }

        public int getChange_recoveries() {
            return this.change_recoveries;
        }

        public void setChange_vaccinations(int change_vaccinations) {
            this.change_vaccinations = change_vaccinations;
        }

        public int getChange_vaccinations() {
            return this.change_vaccinations;
        }

        public void setChange_vaccinated(int change_vaccinated) {
            this.change_vaccinated = change_vaccinated;
        }

        public int getChange_vaccinated() {
            return this.change_vaccinated;
        }

        public void setChange_boosters_1(int change_boosters_1) {
            this.change_boosters_1 = change_boosters_1;
        }

        public int getChange_boosters_1() {
            return this.change_boosters_1;
        }

        public void setChange_vaccines_distributed(int change_vaccines_distributed) {
            this.change_vaccines_distributed = change_vaccines_distributed;
        }

        public int getChange_vaccines_distributed() {
            return this.change_vaccines_distributed;
        }

        public void setTotal_cases(int total_cases) {
            this.total_cases = total_cases;
        }

        public int getTotal_cases() {
            return this.total_cases;
        }

        public void setTotal_fatalities(int total_fatalities) {
            this.total_fatalities = total_fatalities;
        }

        public int getTotal_fatalities() {
            return this.total_fatalities;
        }

        public void setTotal_tests(int total_tests) {
            this.total_tests = total_tests;
        }

        public int getTotal_tests() {
            return this.total_tests;
        }

        public void setTotal_hospitalizations(int total_hospitalizations) {
            this.total_hospitalizations = total_hospitalizations;
        }

        public int getTotal_hospitalizations() {
            return this.total_hospitalizations;
        }

        public void setTotal_criticals(int total_criticals) {
            this.total_criticals = total_criticals;
        }

        public int getTotal_criticals() {
            return this.total_criticals;
        }

        public void setTotal_recoveries(int total_recoveries) {
            this.total_recoveries = total_recoveries;
        }

        public int getTotal_recoveries() {
            return this.total_recoveries;
        }

        public void setTotal_vaccinations(int total_vaccinations) {
            this.total_vaccinations = total_vaccinations;
        }

        public int getTotal_vaccinations() {
            return this.total_vaccinations;
        }

        public void setTotal_vaccinated(int total_vaccinated) {
            this.total_vaccinated = total_vaccinated;
        }

        public int getTotal_vaccinated() {
            return this.total_vaccinated;
        }

        public void setTotal_boosters_1(int total_boosters_1) {
            this.total_boosters_1 = total_boosters_1;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "date='" + date + '\'' +
                    ", change_cases=" + change_cases +
                    ", change_fatalities=" + change_fatalities +
                    ", change_tests=" + change_tests +
                    ", change_hospitalizations=" + change_hospitalizations +
                    ", change_criticals=" + change_criticals +
                    ", change_recoveries=" + change_recoveries +
                    ", change_vaccinations=" + change_vaccinations +
                    ", change_vaccinated=" + change_vaccinated +
                    ", change_boosters_1=" + change_boosters_1 +
                    ", change_vaccines_distributed=" + change_vaccines_distributed +
                    ", total_cases=" + total_cases +
                    ", total_fatalities=" + total_fatalities +
                    ", total_tests=" + total_tests +
                    ", total_hospitalizations=" + total_hospitalizations +
                    ", total_criticals=" + total_criticals +
                    ", total_recoveries=" + total_recoveries +
                    ", total_vaccinations=" + total_vaccinations +
                    ", total_vaccinated=" + total_vaccinated +
                    ", total_boosters_1=" + total_boosters_1 +
                    ", total_vaccines_distributed=" + total_vaccines_distributed +
                    '}';
        }

        public int getTotal_boosters_1() {
            return this.total_boosters_1;
        }

        public void setTotal_vaccines_distributed(int total_vaccines_distributed) {
            this.total_vaccines_distributed = total_vaccines_distributed;
        }

        public int getTotal_vaccines_distributed() {
            return this.total_vaccines_distributed;
        }


    }
}
