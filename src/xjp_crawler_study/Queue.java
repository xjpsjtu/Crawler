package xjp_crawler_study;

import java.util.LinkedList;
public class Queue {
	/**
	 * 定义一个队列，使用LinkedList实现
	 */
	private LinkedList<Object> queue = new LinkedList<Object>();
	/**
	 * 将t加入到队列中
	 * @param t
	 */
	public void enQueue(Object t){
		queue.add(t);
	}
	/**
	 * 移除队列中第一项并将其返回
	 */
	public Object deQueue(){
		return queue.removeFirst();
	}
	/**
	 * 判断队列是否为空
	 */
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	/**
	 * 判断并返回队列是否包含t
	 */
	public boolean contains(Object t){
		return queue.contains(t);
	}
}
