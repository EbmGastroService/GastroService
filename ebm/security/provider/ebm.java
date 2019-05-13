// created on 04.07.2003 at 11:06#
/*
 * @(#)Sun.java	1.28 99/05/27
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package ebm.security.provider;

import java.io.*;
import java.util.*;
import java.security.*;

/**
 * The SUN Security Provider.
 *
 * @author Benjamin Renaud 
 *
 * @version 1.28, 05/27/99
 */

/**
 * Defines the SUN provider.
 *
 * Algorithms supported, and their names:
 *
 * - SHA is the message digest scheme described in FIPS 180-1. 
 *   Aliases for SHA are SHA-1 and SHA1.
 *
 * - SHA1withDSA is the signature scheme described in FIPS 186.
 *   (SHA used in DSA is SHA-1: FIPS 186 with Change No 1.)
 *   Aliases for SHA1withDSA are DSA, DSS, SHA/DSA, SHA-1/DSA, SHA1/DSA,
 *   SHAwithDSA, DSAWithSHA1, and the object
 *   identifier strings "OID.1.3.14.3.2.13", "OID.1.3.14.3.2.27" and
 *   "OID.1.2.840.10040.4.3".
 *
 * - DSA is the key generation scheme as described in FIPS 186.
 *   Aliases for DSA include the OID strings "OID.1.3.14.3.2.12"
 *   and "OID.1.2.840.10040.4.1".
 *
 * - MD5 is the message digest scheme described in RFC 1321.
 *   There are no aliases for MD5.
 */

public final class ebm extends Provider {

    private static final String INFO = "EBM" + 
    "(DSA key/parameter generation; DSA signing; " +
    "SHA-1, MD5 digests; SecureRandom; X.509 certificates; JKS keystore)";

    public ebm() {
	/* We are the SUN provider */
	super("ebm", 1.2, INFO);

	AccessController.doPrivileged(new java.security.PrivilegedAction() {
	    public Object run() {

	        /*
	         * Signature engines 
	         */
	        put("Signature.SHA1withDSA", "ebm.security.provider.DSA");
	    
	        put("Alg.Alias.Signature.DSA", "SHA1withDSA");
	        put("Alg.Alias.Signature.DSS", "SHA1withDSA");
	        put("Alg.Alias.Signature.SHA/DSA", "SHA1withDSA");
	        put("Alg.Alias.Signature.SHA-1/DSA", "SHA1withDSA");
	        put("Alg.Alias.Signature.SHA1/DSA", "SHA1withDSA");
	        put("Alg.Alias.Signature.SHAwithDSA", "SHA1withDSA");
	        put("Alg.Alias.Signature.DSAWithSHA1", "SHA1withDSA");
	        put("Alg.Alias.Signature.OID.1.2.840.10040.4.3",
		    "SHA1withDSA");
	        put("Alg.Alias.Signature.1.2.840.10040.4.3", "SHA1withDSA");
	        put("Alg.Alias.Signature.1.3.14.3.2.13", "SHA1withDSA");
	        put("Alg.Alias.Signature.1.3.14.3.2.27", "SHA1withDSA");
	    
	        /*
	         *  Key Pair Generator engines 
	         */
                put("KeyPairGenerator.DSA", 
	            "ebm.security.provider.DSAKeyPairGenerator");
                put("Alg.Alias.KeyPairGenerator.OID.1.2.840.10040.4.1", "DSA");
                put("Alg.Alias.KeyPairGenerator.1.2.840.10040.4.1", "DSA");
                put("Alg.Alias.KeyPairGenerator.1.3.14.3.2.12", "DSA");

	        /* 
	         * Digest engines 
	         */
	        put("MessageDigest.MD5", "ebm.security.provider.MD5");
	        put("MessageDigest.SHA", "ebm.security.provider.SHA");
	
	        put("Alg.Alias.MessageDigest.SHA-1", "SHA");
	        put("Alg.Alias.MessageDigest.SHA1", "SHA");

		/*
		 * Algorithm Parameter Generator engines
		 */
		put("AlgorithmParameterGenerator.DSA",
		    "ebm.security.provider.DSAParameterGenerator");

		/*
		 * Algorithm Parameter engines
		 */
		put("AlgorithmParameters.DSA",
		    "ebm.security.provider.DSAParameters");
		put("Alg.Alias.AlgorithmParameters.1.3.14.3.2.12", "DSA");
		put("Alg.Alias.AlgorithmParameters.1.2.840.10040.4.1", "DSA");

	        /*
	         * Key factories
	         */
	        put("KeyFactory.DSA", "ebm.security.provider.DSAKeyFactory");
                put("Alg.Alias.KeyFactory.1.3.14.3.2.12", "DSA");
                put("Alg.Alias.KeyFactory.1.2.840.10040.4.1", "DSA");

	        /*
	         * SecureRandom
	         */
	         put("SecureRandom.SHA1PRNG",
		     "ebm.security.provider.SecureRandom");

		/*
		 * Certificates
		 */
		put("CertificateFactory.X509",
		    "ebm.security.provider.X509Factory");
		put("Alg.Alias.CertificateFactory.X.509", "X509");

		/*
		 * KeyStore
		 */
		put("KeyStore.JKS", "ebm.security.provider.JavaKeyStore");

		/*
		 * KeySize
		 */
		put("Signature.SHA1withDSA KeySize", "1024");
		put("KeyPairGenerator.DSA KeySize", "1024");
		put("AlgorithmParameterGenerator.DSA KeySize", "1024");

		/*
		 * Implementation type: software or hardware
		 */
		put("Signature.SHA1withDSA ImplementedIn", "Software");
		put("KeyPairGenerator.DSA ImplementedIn", "Software");
		put("MessageDigest.MD5 ImplementedIn", "Software");
		put("MessageDigest.SHA ImplementedIn", "Software");
		put("AlgorithmParameterGenerator.DSA ImplementedIn", 
		    "Software");
		put("AlgorithmParameters.DSA ImplementedIn", "Software");
		put("KeyFactory.DSA ImplementedIn", "Software");
		put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
		put("CertificateFactory.X509 ImplementedIn", "Software");
		put("KeyStore.JKS ImplementedIn", "Software");

		return null;
	    }
	});
    }
}


