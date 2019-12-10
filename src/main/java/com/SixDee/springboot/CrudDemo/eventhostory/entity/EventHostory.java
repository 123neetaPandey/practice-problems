package com.SixDee.springboot.CrudDemo.eventhostory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="eventhistory")
public class EventHostory {

	
	
	//define fields 
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="Serial_No")
		private int Serial_No;
		
		@Column(name="Account_Id")
		private int Account_Id;
		
		@Column(name="Call_Type")
		private String Call_Type;
		
		@Column(name="Service_Type")
		private String Service_Type;
		
		@Column(name="Start_Time")
		private String Start_Time;
		
		@Column(name="End_Time")
		private String End_Time;
		
		@Column(name="Call_Duration")
		private String Call_Duration;
		
		@Column(name="Dialed_Digit")
		private String Dialed_Digit;
		
		public EventHostory() {
			
		}
		public EventHostory(int serial_No, int account_Id, String call_Type, String service_Type, String start_Time,
				String end_Time, String call_Duration, String dialed_Digit) {
			super();
			Serial_No = serial_No;
			Account_Id = account_Id;
			Call_Type = call_Type;
			Service_Type = service_Type;
			Start_Time = start_Time;
			End_Time = end_Time;
			Call_Duration = call_Duration;
			Dialed_Digit = dialed_Digit;
		}


		public int getSerial_No() {
			return Serial_No;
		}

		public void setSerial_No(int serial_No) {
			Serial_No = serial_No;
		}

		public int getAccount_Id() {
			return Account_Id;
		}

		public void setAccount_Id(int account_Id) {
			Account_Id = account_Id;
		}

		public String getCall_Type() {
			return Call_Type;
		}

		public void setCall_Type(String call_Type) {
			Call_Type = call_Type;
		}

		public String getService_Type() {
			return Service_Type;
		}

		public void setService_Type(String service_Type) {
			Service_Type = service_Type;
		}

		public String getStart_Time() {
			return Start_Time;
		}

		public void setStart_Time(String start_Time) {
			Start_Time = start_Time;
		}

		public String getEnd_Time() {
			return End_Time;
		}

		public void setEnd_Time(String end_Time) {
			End_Time = end_Time;
		}

		public String getCall_Duration() {
			return Call_Duration;
		}

		public void setCall_Duration(String call_Duration) {
			Call_Duration = call_Duration;
		}

		public String getDialed_Digit() {
			return Dialed_Digit;
		}

		public void setDialed_Digit(String dialed_Digit) {
			Dialed_Digit = dialed_Digit;
		}

		
		@Override
		public String toString() {
			return "EventHostory [Serial_No=" + Serial_No + ", Account_Id=" + Account_Id + ", Call_Type=" + Call_Type
					+ ", Service_Type=" + Service_Type + ", Start_Time=" + Start_Time + ", End_Time=" + End_Time
					+ ", Call_Duration=" + Call_Duration + ", Dialed_Digit=" + Dialed_Digit + "]";
		}
		
		
		
		
		
		
		
		
}
