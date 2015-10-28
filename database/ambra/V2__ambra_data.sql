
/* pre: ned schema hasn't been created yet (by flyway migrations) */
INSERT INTO ambra.userProfile (userProfileID, userProfileURI, authId, realName, givenNames, 
                               surName, email, displayName, biography, created, lastModified, password)
    VALUES (1, 'info:doi/10.1371/profile/09dcb739-00c2-497a-8470-fc0bc4722b0e', UUID(),
        'Cosmo Kramer','Cosmo','Kramer','ckramer@plos.org','ckramer','a short bio', NOW(), NOW(), 
        'f953d98b896b3739bf925346cffb4e2e9b5e724d14bc66bc6562672944ae21d1f76bf904416af9ce52ddff3d9b46b447a4d1d6e17318299e967d0362d0df0ad6');
