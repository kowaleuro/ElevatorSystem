# ElevatorSystem
Simple Elevator System

## Table of Contents
* [Description](#description)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Manual](#manual)

## Description

Simple elevator control system with simulation to show how it works. Algorythm chosen to run the elevators is not FCFS, because it would be very inefficient. So the algorythm takes the pickup request consisting of current floor and destination floor as ArrayList of 2 elements [destination,current] and adds it to Request ArrayList. Every time elevator goes to next floor, it checks if there is a floor at the last index of an List in his request ArrayList equal to elevator's current floor. If this happens at least once, the doors of the elevator will stop at this floor and every element at the last index will be removed of this two-element List. If List contained only one element (destination floor) this whole List is also removed from Request List. This prevents the situation, where the elevator would stop at the destination floor without picking people up from their's current floor earlier. Moreover, because of this the elevator will stop every time there is somebody to drop or pick up at the floor. 

## Screenshots

## Setup

## Manual
