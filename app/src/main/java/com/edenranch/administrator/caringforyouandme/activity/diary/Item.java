package com.edenranch.administrator.caringforyouandme.activity.diary;

import java.time.LocalDateTime;

public class Item {
	int image;
	int Seq;
	String Subject;
	String Content;
	String ID;
	String insertDT;

	int getImage() {
		return this.image;
	}
	int getSeq() {
		return this.Seq;
	}
	String getSubject() {
		return this.Subject;
	}
	String getContent() {
		return this.Content;
	}
	String getID() {
		return this.ID;
	}
	String getinsertDT() {
		return this.insertDT;
	}

	// 저장
	public Item(int image, int Seq, String Subject, String Content, String ID, String insertDT) {
		this.image = image;
		this.Seq = Seq;
		this.Subject = Subject;
		this.Content = Content;
		this.ID = ID;
		this.insertDT = insertDT;
	}

}
