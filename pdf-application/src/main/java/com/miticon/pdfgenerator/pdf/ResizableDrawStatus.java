package com.miticon.pdfgenerator.pdf;

public class ResizableDrawStatus {

    private boolean finished;
    private int drawnLines;
    private int sizeOnCurrentPage;

    public ResizableDrawStatus(boolean finished, int drawnLines, int sizeOnCurrentPage) {
        this.finished = finished;
        this.drawnLines = drawnLines;
        this.sizeOnCurrentPage = sizeOnCurrentPage;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getDrawnLines() {
        return drawnLines;
    }

    public void setDrawnLines(int drawnLines) {
        this.drawnLines = drawnLines;
    }

    public int getSizeOnCurrentPage() {
        return sizeOnCurrentPage;
    }

    public void setSizeOnCurrentPage(int sizeOnCurrentPage) {
        this.sizeOnCurrentPage = sizeOnCurrentPage;
    }

}
