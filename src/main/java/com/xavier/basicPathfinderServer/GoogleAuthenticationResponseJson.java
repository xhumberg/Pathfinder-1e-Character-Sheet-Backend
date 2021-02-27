package com.xavier.basicPathfinderServer;

public class GoogleAuthenticationResponseJson {
	public final String iss;
	public final String azp;
	public final String aud;
	public final String sub;
	public final String email;
	public final String email_verified;
	public final String at_hash;
	public final String name;
	public final String picture;
	public final String given_name;
	public final String family_name;
	public final String locale;
	public final String iat;
	public final String exp;
	public final String jti;
	public final String alg;
	public final String kid;
	public final String typ;

	public GoogleAuthenticationResponseJson(String iss, String azp, String aud, String sub, String email,
			String email_verified, String at_hash, String name, String picture, String given_name, String family_name,
			String locale, String iat, String exp, String jti, String alg, String kid, String typ) {
		super();
		this.iss = iss;
		this.azp = azp;
		this.aud = aud;
		this.sub = sub;
		this.email = email;
		this.email_verified = email_verified;
		this.at_hash = at_hash;
		this.name = name;
		this.picture = picture;
		this.given_name = given_name;
		this.family_name = family_name;
		this.locale = locale;
		this.iat = iat;
		this.exp = exp;
		this.jti = jti;
		this.alg = alg;
		this.kid = kid;
		this.typ = typ;
	}
	
	public String getIss() {
		return iss;
	}

	public String getAzp() {
		return azp;
	}

	public String getAud() {
		return aud;
	}

	public String getSub() {
		return sub;
	}

	public String getEmail() {
		return email;
	}

	public String getEmail_verified() {
		return email_verified;
	}

	public String getAt_hash() {
		return at_hash;
	}

	public String getName() {
		return name;
	}

	public String getPicture() {
		return picture;
	}

	public String getGiven_name() {
		return given_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public String getLocale() {
		return locale;
	}

	public String getIat() {
		return iat;
	}

	public String getExp() {
		return exp;
	}

	public String getJti() {
		return jti;
	}

	public String getAlg() {
		return alg;
	}

	public String getKid() {
		return kid;
	}

	public String getTyp() {
		return typ;
	}
}
