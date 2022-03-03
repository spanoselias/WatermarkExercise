package com.watermark.model;

import com.watermark.dto.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Processing")
public class Processing {

    @Id
    @GeneratedValue
    private Long id;

    private String ticketId;
    private Status status;

    public Processing() {
    }

    public Processing(String ticketId, Status status) {
        this.ticketId = ticketId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String submittedId) {
        this.ticketId = submittedId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
