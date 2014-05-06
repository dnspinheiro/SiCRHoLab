/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Disciplina;
import org.eclipse.persistence.sessions.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maycon
 */
public class DisciplinaMBTest {
    
    private DisciplinaMB inserirMB = new DisciplinaMB();
    private DisciplinaMB alterarMB = new DisciplinaMB();
    private DisciplinaMB excluirMB = new DisciplinaMB();
    private DisciplinaMB testeMB = new DisciplinaMB();
    //Criar apenas com um MB
    
    public DisciplinaMBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        inserirMB.getDisciplina().setCod_disciplina("BSI2101");
        inserirMB.getDisciplina().setCarga_horaria(60);
        inserirMB.getDisciplina().setCurso("Sistemas de Informação");
        inserirMB.getDisciplina().setNome("Redes");
        
        alterarMB.getDisciplina().setCod_disciplina("BSI2102");
        alterarMB.getDisciplina().setCarga_horaria(60);
        alterarMB.getDisciplina().setCurso("Sistemas de Informação");
        alterarMB.getDisciplina().setNome("Arquitetura");
        
        excluirMB.getDisciplina().setCod_disciplina("BSI2103");
        excluirMB.getDisciplina().setCarga_horaria(60);
        excluirMB.getDisciplina().setCurso("Sistemas de Informação");
        excluirMB.getDisciplina().setCurso("Programação");
        
        testeMB.getDisciplina().setCod_disciplina("BSI2104");
        testeMB.getDisciplina().setCarga_horaria(60);
        testeMB.getDisciplina().setCurso("Sistemas de Informação");
        testeMB.getDisciplina().setCurso("Lógica");
    }
    
    @After
    public void tearDown() {
        try {
            inserirMB.disciplinaDAO.destroy("BSI2101");
            alterarMB.disciplinaDAO.destroy("BSI2102");
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DisciplinaMBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of inserirDisciplina method, of class DisciplinaMB.
     */
    @Test
    public void testInserirDisciplina() {
        Disciplina d = inserirMB.getDisciplina();
        inserirMB.inserirDisciplina();
        assertEquals(d, inserirMB.disciplinaDAO.findDisciplina("BSI2101"));
    }

    /**
     * Test of alterarDisciplina method, of class DisciplinaMB.
     */
    @Test
    public void testAlterarDisciplina() {
        alterarMB.getDisciplina().setNome("ESII");
        Disciplina d = alterarMB.getDisciplina();
        alterarMB.alterarDisciplina();
        assertEquals(d, alterarMB.disciplinaDAO.findDisciplina(d.getCod_disciplina()));
    }

    /**
     * Test of excluirDisciplina method, of class DisciplinaMB.
     */
    @Test
    public void testExcluirDisciplina() {
        Disciplina d = excluirMB.getDisciplina();
        excluirMB.excluirDisciplina();
        assertNull(excluirMB.disciplinaDAO.findDisciplina(d.getCod_disciplina()));
    }

    /**
     * Test of pesquisar method, of class DisciplinaMB.
     */
    @Test
    public void testPesquisar() {
        testeMB.pesquisar();
        List<Disciplina> ds = testeMB.disciplinaDAO.findDisciplinaEntities();
        assertEquals(ds, testeMB.getDisciplinas());
    }

    /**
     * Test of pesquisarPorNomeDaDisciplina method, of class DisciplinaMB.
     */
    @Test
    public void testPesquisarPorNomeDaDisciplina() {
        List<Disciplina> ds = new ArrayList<Disciplina>();
        for(Disciplina s : testeMB.disciplinaDAO.findDisciplinaEntities()){
            if(s.getNome().toUpperCase().contains(testeMB.getDisciplina().getCod_disciplina().toUpperCase())){
                ds.add(s);
            }
        }
        testeMB.pesquisarPorNomeDaDisciplina();
        assertEquals(ds, testeMB.getDisciplinas());
    }

    /**
     * Test of pesquisarDisciplinaPorCurso method, of class DisciplinaMB.
     */
    @Test
    public void testPesquisarDisciplinaPorCurso() {
        System.out.println("pesquisarDisciplinaPorCurso");
        DisciplinaMB instance = new DisciplinaMB();
        instance.setCurso_disciplina("Sistemas de Informação");
        instance.pesquisarDisciplinaPorCurso();
        List<Disciplina> d = new ArrayList<Disciplina>();
        for(Disciplina s : instance.disciplinaDAO.findDisciplinaEntities()){
            if(s.getCurso().toUpperCase().contains(instance.getCurso_disciplina().toUpperCase())){
                d.add(s);
            }
        }
        assertEquals(d, instance.getDisciplinas());
    }

    /**
     * Test of getDisciplina method, of class DisciplinaMB.
     */
    @Test
    public void testGetDisciplina() {
        System.out.println("getDisciplina");
        DisciplinaMB instance = new DisciplinaMB();
        Disciplina expResult = instance.getDisciplina();
        Disciplina result = instance.getDisciplina();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDisciplina method, of class DisciplinaMB.
     */
    @Test
    public void testSetDisciplina() {
        System.out.println("setDisciplina");
        Disciplina disciplina = new Disciplina();
        DisciplinaMB instance = new DisciplinaMB();
        instance.setDisciplina(disciplina);
        assertEquals(disciplina, instance.getDisciplina());
    }

    /**
     * Test of getDisciplinas method, of class DisciplinaMB.
     */
    @Test
    public void testGetDisciplinas() {
        System.out.println("getDisciplinas");
        DisciplinaMB instance = new DisciplinaMB();
        List expResult = instance.getDisciplinas();
        List result = instance.getDisciplinas();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDisciplinas method, of class DisciplinaMB.
     */
    @Test
    public void testSetDisciplinas() {
        System.out.println("setDisciplinas");
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        DisciplinaMB instance = new DisciplinaMB();
        instance.setDisciplinas(disciplinas);
        assertEquals(disciplinas, instance.getDisciplinas());
    }

    /**
     * Test of getDisciplinaPesquisada method, of class DisciplinaMB.
     */
    @Test
    public void testGetDisciplinaPesquisada() {
        System.out.println("getDisciplinaPesquisada");
        DisciplinaMB instance = new DisciplinaMB();
        String expResult = instance.getDisciplinaPesquisada();
        String result = instance.getDisciplinaPesquisada();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDisciplinaPesquisada method, of class DisciplinaMB.
     */
    @Test
    public void testSetDisciplinaPesquisada() {
        System.out.println("setDisciplinaPesquisada");
        String disciplinaPesquisada = "Redes";
        DisciplinaMB instance = new DisciplinaMB();
        instance.setDisciplinaPesquisada(disciplinaPesquisada);
        assertEquals(disciplinaPesquisada, instance.getDisciplinaPesquisada());
    }

    /**
     * Test of getMensagem method, of class DisciplinaMB.
     */
    @Test
    public void testGetMensagem() {
        System.out.println("getMensagem");
        DisciplinaMB instance = new DisciplinaMB();
        String expResult = instance.getMensagem();
        String result = instance.getMensagem();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMensagem method, of class DisciplinaMB.
     */
    @Test
    public void testSetMensagem() {
        System.out.println("setMensagem");
        String mensagem = "Teste";
        DisciplinaMB instance = new DisciplinaMB();
        instance.setMensagem(mensagem);
        assertEquals(mensagem, instance.getMensagem());
    }

    /**
     * Test of getNome_disciplina method, of class DisciplinaMB.
     */
    @Test
    public void testGetNome_disciplina() {
        System.out.println("getNome_disciplina");
        DisciplinaMB instance = new DisciplinaMB();
        String expResult = instance.getNome_disciplina();
        String result = instance.getNome_disciplina();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNome_disciplina method, of class DisciplinaMB.
     */
    @Test
    public void testSetNome_disciplina() {
        System.out.println("setNome_disciplina");
        String nome_disciplina = "Redes";
        DisciplinaMB instance = new DisciplinaMB();
        instance.setNome_disciplina(nome_disciplina);
        assertEquals(nome_disciplina, instance.getNome_disciplina());
    }

    /**
     * Test of getCurso_disciplina method, of class DisciplinaMB.
     */
    @Test
    public void testGetCurso_disciplina() {
        System.out.println("getCurso_disciplina");
        DisciplinaMB instance = new DisciplinaMB();
        String expResult = instance.getCurso_disciplina();
        String result = instance.getCurso_disciplina();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurso_disciplina method, of class DisciplinaMB.
     */
    @Test
    public void testSetCurso_disciplina() {
        System.out.println("setCurso_disciplina");
        String curso_disciplina = "Sistemas de Informação";
        DisciplinaMB instance = new DisciplinaMB();
        instance.setCurso_disciplina(curso_disciplina);
        assertEquals(curso_disciplina, instance.getCurso_disciplina());
    }
}
