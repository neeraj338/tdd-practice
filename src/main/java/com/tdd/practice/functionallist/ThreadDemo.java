package com.tdd.practice.functionallist;
class ThreadDemo2 extends Thread 
{ 
	ThreadDemo2() {} 
	ThreadDemo2(Runnable r) {super(r); } 
    public synchronized void run() 
    { 
        System.out.print("Inside Thread ");
    } 
} 
class RunnableDemo implements Runnable 
{ 
    public void run() 
    { 
        System.out.print(" Inside Runnable"); 
    } 
} 
public class ThreadDemo 
{  
    public static void main(String[] args) 
    { 
        new ThreadDemo2().start(); 
        new ThreadDemo2(new RunnableDemo()).start(); 
    } 
} 