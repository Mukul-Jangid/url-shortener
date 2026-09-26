package com.urlshortener.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.Objects;

/**
 * Domain entity representing a shortened URL link. Holds the original web address, the generated
 * short code, creation timestamp, and active status.
 */
@Entity
@Table(
    name = "short_urls",
    indexes = {@Index(name = "idx_short_url_code", columnList = "code", unique = true)})
public class ShortUrl {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "code", nullable = false, unique = true, length = 32)
  private String code;

  @Column(name = "original_url", nullable = false, length = 2048)
  private String originalUrl;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Column(name = "active", nullable = false)
  private boolean active;

  /** Default constructor required by JPA. */
  public ShortUrl() {}

  /**
   * Convenient constructor for creating a new active ShortUrl with current timestamp.
   *
   * @param code Unique short identifier string.
   * @param originalUrl Original destination web address.
   */
  public ShortUrl(String code, String originalUrl) {
    this(code, originalUrl, Instant.now(), true);
  }

  /**
   * Constructs a new ShortUrl instance.
   *
   * @param code Unique short identifier string.
   * @param originalUrl Original destination web address.
   * @param createdAt Time when this short link was created.
   * @param active True if link can be used for redirecting; false if deactivated.
   */
  public ShortUrl(String code, String originalUrl, Instant createdAt, boolean active) {
    this.code = code;
    this.originalUrl = originalUrl;
    this.createdAt = createdAt;
    this.active = active;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ShortUrl shortUrl = (ShortUrl) o;
    return id != null && Objects.equals(id, shortUrl.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
