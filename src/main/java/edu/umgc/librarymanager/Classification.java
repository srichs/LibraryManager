/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umgc.librarymanager;

/**
 *
 * @author David
 */
public interface Classification {
    public enum ClassType {
        DeweyDecimal,
        LibraryOfCongress
    };
    
    public String getCode();
}
