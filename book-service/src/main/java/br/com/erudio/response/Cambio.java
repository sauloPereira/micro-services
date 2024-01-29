package br.com.erudio.response;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cambio implements Serializable {

    private Long id;
    private String from;
    private String to;
    private Double conversionFactor;
    private Double conversionValue;
    private String environment;

    public Cambio() { }

    public Cambio(Long id, String from, String to, Double conversionFactor, Double conversionValue, String environment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.conversionValue = conversionValue;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public Double getConversionValue() {
        return conversionValue;
    }

    public void setConversionValue(Double conversionValue) {
        this.conversionValue = conversionValue;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cambio cambio = (Cambio) o;

        if (getId() != null ? !getId().equals(cambio.getId()) : cambio.getId() != null) return false;
        if (getFrom() != null ? !getFrom().equals(cambio.getFrom()) : cambio.getFrom() != null) return false;
        if (getTo() != null ? !getTo().equals(cambio.getTo()) : cambio.getTo() != null) return false;
        if (getConversionFactor() != null ? !getConversionFactor().equals(cambio.getConversionFactor()) : cambio.getConversionFactor() != null)
            return false;
        if (getConversionValue() != null ? !getConversionValue().equals(cambio.getConversionValue()) : cambio.getConversionValue() != null)
            return false;
        return getEnvironment() != null ? getEnvironment().equals(cambio.getEnvironment()) : cambio.getEnvironment() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFrom() != null ? getFrom().hashCode() : 0);
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (getConversionFactor() != null ? getConversionFactor().hashCode() : 0);
        result = 31 * result + (getConversionValue() != null ? getConversionValue().hashCode() : 0);
        result = 31 * result + (getEnvironment() != null ? getEnvironment().hashCode() : 0);
        return result;
    }
}
