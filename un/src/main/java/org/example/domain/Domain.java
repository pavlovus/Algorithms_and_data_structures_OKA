package org.example.domain;

public class Domain implements Comparable<Domain> {
    private String domain;

    public Domain(String domain) {
        StringBuilder reverseDomain = new StringBuilder();
        String[] parts = domain.split("\\.");
        for (int i = parts.length - 1; i >= 0; i--) {
            reverseDomain.append(parts[i]);
            if (i > 0) reverseDomain.append(".");
        }
        this.domain = reverseDomain.toString();
    }

    @Override
    public int compareTo(Domain o) {return domain.compareTo(o.domain);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domain domain = (Domain) o;
        return this.domain.equals(domain.domain);
    }

    @Override
    public int hashCode() {return domain.hashCode();}

    public String toString() {return domain;}
}