package com.bae.raziel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GraphOption {

	private Title title = new Title();
	private AxisX axisX = new AxisX();
	private ArrayList data ;
	
	public GraphOption() {
		super();
		// TODO Auto-generated constructor stub
		data = new ArrayList<Data>();
		Data dataObject = new Data();
		data.add(dataObject);
	}
	
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public AxisX getAxisX() {
		return axisX;
	}
	public void setAxisX(AxisX axisX) {
		this.axisX = axisX;
	}
	public ArrayList getData() {
		return data;
	}
	public void setData(ArrayList data) {
		this.data = data;
	}
	public DataPoint getNewDataPoint() {
		return new DataPoint();
	}

	public class Title {
		private String text = "Alter - per day";
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}

	public class AxisX {
		private String valueFormatString = "MMM-DD";
		private int interval = 1;
		private String intervalType = "day";
		public String getValueFormatString() {
			return valueFormatString;
		}
		public void setValueFormatString(String valueFormatString) {
			this.valueFormatString = valueFormatString;
		}
		public int getInterval() {
			return interval;
		}
		public void setInterval(int interval) {
			this.interval = interval;
		}
		public String getIntervalType() {
			return intervalType;
		}
		public void setIntervalType(String intervalType) {
			this.intervalType = intervalType;
		}
		
	}
	
	public class Data {
		private String type = "line";
		private ArrayList<DataPoint> dataPoints;
		public Data() {
			super();
			dataPoints = new ArrayList<DataPoint>();
//			DataPoint dataPoint = new DataPoint();
//			dataPoints.add(dataPoint);
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public ArrayList getDataPoints() {
			return dataPoints;
		}
		public void setDataPoints(ArrayList dataPoints) {
			this.dataPoints = dataPoints;
		}
	}
	
	public class DataPoint{
		private String x ;
		private int y = 0;
		public String getX() {
			return x;
		}
		public void setX(String x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
	}
	
}

