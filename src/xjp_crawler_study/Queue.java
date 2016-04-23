package xjp_crawler_study;

import java.util.LinkedList;
public class Queue {
	/**
	 * ����һ�����У�ʹ��LinkedListʵ��
	 */
	private LinkedList<Object> queue = new LinkedList<Object>();
	/**
	 * ��t���뵽������
	 * @param t
	 */
	public void enQueue(Object t){
		queue.add(t);
	}
	/**
	 * �Ƴ������е�һ����䷵��
	 */
	public Object deQueue(){
		return queue.removeFirst();
	}
	/**
	 * �ж϶����Ƿ�Ϊ��
	 */
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	/**
	 * �жϲ����ض����Ƿ����t
	 */
	public boolean contains(Object t){
		return queue.contains(t);
	}
}
