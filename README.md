# banking
Improved version of Stocks and Shares
An Improved version of the stocks and shares example with better code and more comments

Stocks and Shares Program
Many thanks for your interesting specification. I am not sure exactly what you are looking
for so I have added more comments to the code than I normally would. I believe variable, 
class and method names should explain the ‘What’, the code the ‘How’ and comments the ‘Why’.

I had wondered if the specification was also a test to see if the developer was up to speed 
with Java 8 streams.  I have included some examples of stream usage but I think in my case
the non-stream version were more readable with a method for each equation.  The commented 
out Java 8 stream code does works but you need to make the trade record class public.
If someone has a good neat stream solution to your specification I would be most 
interested just from a professional point of view to see it. There is always someone out 
there who is better.

The key cornerstone is the design of the log structure. For this we have not received the
full specifications particularly the dimensions of the log data, frequency break down etc.
However all the reporting requested from the log is grouped by stock symbol. Therefore a 
log keyed on symbol appears to be the most useful structure. The log record has a pointer 
to a stock object rather than the particular types of stock or values. This keeps the log 
record design flexible if new types of stock are added (The Java manta of programming to 
interfaces). The log record and stock types are immutable. This appears reasonable as they
are historical snapshots of some trade. They are also easier to debug. The stock classes
and log records have hash and equals methods added because they are in reality distinct
entities that could appear in other types of collections at a later date. As most IDEs 
can generate these methods for you they might as well be added when the class is created 
even if not used currently. Arguably they should be serializable and with a serial version
number as well.

The software was developed with in Netbeans 8.1 but should work with any IDE.
