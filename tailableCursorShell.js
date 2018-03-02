/*
	This gets around timeouts in the shell to demonstrate the behavior of a tailable cursor
*/

var cursor = db.capped.find().addOption(DBQuery.Option.tailable).addOption(DBQuery.Option.awaitData)
while ( true ) { 
    while ( cursor.hasNext() ) {
       printjson( cursor.next() );
    }
}