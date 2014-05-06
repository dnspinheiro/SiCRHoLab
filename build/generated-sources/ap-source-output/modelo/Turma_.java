package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Disciplina;
import modelo.Usuario;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-06-17T15:28:16")
@StaticMetamodel(Turma.class)
public class Turma_ { 

    public static volatile SingularAttribute<Turma, Long> cod_turma;
    public static volatile SingularAttribute<Turma, Disciplina> disciplina;
    public static volatile SingularAttribute<Turma, Integer> ano_letivo;
    public static volatile SingularAttribute<Turma, Usuario> usuario;
    public static volatile SingularAttribute<Turma, Integer> numero_turma;

}