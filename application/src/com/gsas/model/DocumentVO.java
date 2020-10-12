package com.gsas.model;

public class DocumentVO implements Comparable<DocumentVO>{
	
	private long documentId;
	private String documentName;
	public DocumentVO() {
		super();
	}
	public DocumentVO(long documentId) {
		super();
		this.documentId = documentId;
	}
	public DocumentVO(long documentId, String documentName) {
		super();
		this.documentId = documentId;
		this.documentName = documentName;
	}
	public long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	@Override
	public String toString() {
		return "DocumentVO [documentId=" + documentId + ", documentName=" + documentName + "]";
	}
	@Override
	public int compareTo(DocumentVO o) {
		// TODO Auto-generated method stub
		int result;
		if(this.documentId-o.documentId == 0)
			result = 0;
		else
			result = 1;
		return result;
	}
	
}
