package energyservice;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Dashboard_table")
public class Dashboard {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long reservationid;
        private Long centerid;
        private Long agentid;
        private String mdate;
        private String mtime;
        private String edate;
        private String etime;
        private String status;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getReservationid() {
            return reservationid;
        }

        public void setReservationid(Long reservationid) {
            this.reservationid = reservationid;
        }
        public Long getCenterid() {
            return centerid;
        }

        public void setCenterid(Long centerid) {
            this.centerid = centerid;
        }
        public Long getAgentid() {
            return agentid;
        }

        public void setAgentid(Long agentid) {
            this.agentid = agentid;
        }
        public String getMdate() {
            return mdate;
        }

        public void setMdate(String mdate) {
            this.mdate = mdate;
        }
        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }
        public String getEdate() {
            return edate;
        }

        public void setEdate(String edate) {
            this.edate = edate;
        }
        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

}
