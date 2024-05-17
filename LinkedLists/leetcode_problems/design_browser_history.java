// https://leetcode.com/problems/design-browser-history/description/

import java.util.ArrayList;
import java.util.List;

class BrowserHistory {
    private List<String> history;
    private int currentURL;
    private int lastURL;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        currentURL = 0;
        lastURL = 0;
    }
    
    public void visit(String url) {
        currentURL++;
        if (history.size() > currentURL) {
            history.set(currentURL, url);
        }
        else {
            history.add(url);
        }
        lastURL = currentURL;
    }
    
    public String back(int steps) {
        // The below could also be done using        
        // currentURL = Math.max(0, currentURL - steps);

        if (currentURL - steps > 0) {
            currentURL = currentURL - steps;
        }
        else {
            currentURL = 0;
        }
        return history.get(currentURL);
    }
    
    public String forward(int steps) {
        // This could be simplified to Math.min(lastURL, currentURL + steps);

        if (currentURL + steps <= lastURL) {
            currentURL = currentURL + steps;
        }
        else {
            currentURL = lastURL;
        }
        return history.get(currentURL);
    }
}
