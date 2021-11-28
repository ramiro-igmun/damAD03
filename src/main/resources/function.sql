CREATE FUNCTION modifyGrade(argDni CHAR(15), argCode INT, grade TINYINT)
    RETURNS CHAR(50)
BEGIN
    IF EXISTS(SELECT * FROM notas WHERE dni = argDni AND cod = argCode) THEN
        UPDATE notas
        SET nota=grade
        WHERE dni = argDni
          AND cod = argCode;
        RETURN 'Nota modificada correctamente';
    ELSE
        INSERT INTO notas VALUES (argDni, argCode, grade);
        RETURN 'Nota creada correctamente';
    END IF;
END;

