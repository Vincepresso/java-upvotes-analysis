# java-upvotes-analysis

Written in Java.
Quora programming challenges problem #1: https://www.quora.com/challenges#upvotes

Problem: At Quora, we have aggregate graphs that track the number of upvotes we get each day.
As we looked at patterns across windows of certain sizes, we thought about ways to track trends
such as non-decreasing and non-increasing subranges as efficiently as possible. For this problem,
you are given N days of upvote count data, and a fixed window size K. For each window of K days,
from left to right, find the number of non-decreasing subranges within the window minus the number
of non-increasing subranges within the window. A window of days is defined as contiguous range of
days. Thus, there are exactly N−K+1N−K+1 windows where this metric needs to be computed.
A non-decreasing subrange is defined as a contiguous range of indices [a,b][a,b], a<ba<b,
where each element is at least as large as the previous element. A non-increasing subrange is
similarly defined, except each element is at least as large as the next. There are up to K(K−1)/2K(K−1)/2
of these respective subranges within a window, so the metric is bounded by [−K(K−1)/2,K(K−1)/2][−K(K−1)/2,K(K−1)/2].
