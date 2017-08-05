DROP TABLE  IF EXISTS act_id_membership;    
DROP TABLE  IF EXISTS act_id_user;    
DROP TABLE IF EXISTS act_id_group ;
DROP VIEW IF EXISTS act_id_membership ;
DROP VIEW IF EXISTS act_id_user ;
DROP VIEW IF EXISTS act_id_group ;

CREATE OR REPLACE VIEW act_id_user (
  ID_,
  REV_,
  FIRST_,
  LAST_,
  EMAIL_,
  PWD_,
  PICTURE_ID_
) AS 
SELECT 
  CONCAT(R.USER_ID) AS ID_,
  0 AS REV_,
  CONCAT(R.USER_NAME) AS FIRST_,
  '' AS LAST_,
  '' AS EMAIL_,
  R.PASSWORD AS PWD_,
  '' AS PICTURE_ID_ 
FROM
  T_USER R ;

CREATE OR REPLACE VIEW act_id_group (ID_, REV_, NAME_, TYPE_) AS 
SELECT 
  CONCAT(ROLENAME) AS ID_,
  0 AS REV_,
  ROLENAME AS NAME_,
  'assignment' AS TYPE_ 
FROM
  T_ROLE ;

CREATE OR REPLACE VIEW act_id_membership (USER_ID_, GROUP_ID_) AS 
SELECT 
  (SELECT 
    user_name 
  FROM
    t_user r 
  WHERE r.user_id = ur.user_id) AS USER_ID_,
  (SELECT 
    ROLENAME 
  FROM
    t_role r 
  WHERE r.id = ur.role_id) AS GROUP_ID_ 
FROM
  t_user_role ur ;

