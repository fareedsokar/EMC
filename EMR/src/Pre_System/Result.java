package Pre_System;
import java.util.*;

public class Result {
private Bugfix Bugfix;
private Test[] Results;
private Date DateTime;

public Test[] getResults() {
	return Results;
}
public void setResults(Test[] results) {
	Results = results;
}
public Bugfix getBugfix() {
	return Bugfix;
}
public void setBugfix(Bugfix bugfix) {
	Bugfix = bugfix;
}
public Date getDateTime() {
	return DateTime;
}
public void setDateTime(Date dateTime) {
	DateTime = dateTime;
}

}
