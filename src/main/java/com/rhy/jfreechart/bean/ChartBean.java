package com.rhy.jfreechart.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jfree.data.xy.XYSeries;

import java.awt.*;

public class ChartBean 
{
	private XYSeries series;

	/**
	 * 线的索引
	 */
	private int index;

	/**
	 * 线上点的形状
	 */
	private Shape shape;

	/**
	 * 线上点的颜色
	 */
	private Color shapeColor;

	/**
	 * 是否填充点
	 */
	private Boolean shapeFilled;

	/**
	 * 线的颜色
	 * 默认黑色
	 */
	private Color lineColor;

	/**
	 * 线是否可见
	 * 默认可见
	 */
	private Boolean lineVisible;

	/**
	 *线上点是否可见
	 * 默认可见
	 */
	private Boolean shapeVisible;

	/**
	 *线是否是虚线
	 */
	private Boolean lineDash;

	/**
	 *线的宽度
	 */
	private Float lineWidth;

	public Boolean getShapeFilled() 
	{
		if(shapeFilled == null) {
			return false;
		}
		return shapeFilled;
	}
	public void setShapeFilled(Boolean shapeFilled) {
		this.shapeFilled = shapeFilled;
	}
	public Boolean getLineVisible() {
		return lineVisible;
	}
	public void setLineVisible(Boolean lineVisible) {
		this.lineVisible = lineVisible;
	}
	public Boolean getLineDash() {
		return lineDash;
	}
	public void setLineDash(Boolean lineDash) {
		this.lineDash = lineDash;
	}
	public Float getLineWidth() {
		return lineWidth;
	}
	public void setLineWidth(Float lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	/**
	 * 获得线的笔触
	 * @return
	 */
	public Stroke getLineStroke() 
	{
		if(this.lineWidth == null) {
			lineWidth = ChartShape.LINE_THIN;
		}
		if(this.lineDash == null) {
			lineDash = new Boolean(false);
		}
		
		return ChartShape.getLineStoke(lineWidth, this.lineDash);
	}

}
