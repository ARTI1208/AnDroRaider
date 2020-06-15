package ru.art2000.androraider.model.analyzer.smali

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNState
import org.antlr.v4.runtime.atn.PredictionMode
import org.antlr.v4.runtime.misc.IntervalSet

class SmaliErrorStrategy: DefaultErrorStrategy() {

    override fun recover(recognizer: Parser, e: RecognitionException?) {

        println("expected: ${recognizer.expectedTokens.toArray().joinToString { recognizer.tokenNames[it] }}")
//        println("expected: ${recognizer.rul.toArray().joinToString { recognizer.tokenNames[it] }}")
        println("state: ${recognizer.state}")
        println("ctx: ${recognizer.context.javaClass}")

        super.recover(recognizer, e)

//        recognizer.expectedTokensWithinCurrentRule
//
//        if (lastErrorIndex == recognizer.inputStream.index() && lastErrorStates != null &&
//                lastErrorStates.contains(recognizer.state)) {
//            // uh oh, another error at same token index and previously-visited
//            // state in ATN; must be a case where LT(1) is in the recovery
//            // token set so nothing got consumed. Consume a single token
//            // at least to prevent an infinite loop; this is a failsafe.
//            recognizer.consume()
//        }
//
//        lastErrorIndex = recognizer.inputStream.index()
//        if (lastErrorStates == null) lastErrorStates = IntervalSet()
//        lastErrorStates.add(recognizer.state)
//
//        val followSet = IntervalSet().apply {
//            addAll(recognizer.expectedTokens)
//            addAll(getErrorRecoverySet(recognizer))
//        }
//
//        val recoveredTo = consumeUntilResult(recognizer, followSet)
//        recognizer.state = recognizer.atn.states[recoveredTo].stateNumber
//        endErrorCondition(recognizer)
    }

//    override fun recoverInline(recognizer: Parser): Token {
//        println("Recover inline2: ${recognizer.state}")
//        println("Search2 for: ${recognizer.expectedTokens.toArray().joinToString { recognizer.tokenNames[it] }}")
//        return super.recoverInline(recognizer).also {
//            println("Recover inline: ${it.text}")
//        }
//    }

    override fun sync(recognizer: Parser) {
        //TODO: is something needed to be here?

        println("syncState: ${recognizer.state}")

        super.sync(recognizer)

//        val s = recognizer.interpreter.atn.states[recognizer.state]
////		System.err.println("sync @ "+s.stateNumber+"="+s.getClass().getSimpleName());
//        // If already recovering, don't try to sync
//        //		System.err.println("sync @ "+s.stateNumber+"="+s.getClass().getSimpleName());
//        // If already recovering, don't try to sync
//        if (inErrorRecoveryMode(recognizer)) {
//            return
//        }
//
//        val tokens = recognizer.inputStream
//        val la = tokens.LA(1)
//
//        // try cheaper subset first; might get lucky. seems to shave a wee bit off
//
//        // try cheaper subset first; might get lucky. seems to shave a wee bit off
//        val nextTokens = recognizer.atn.nextTokens(s)
//        if (nextTokens.contains(la)) {
//            // We are sure the token matches
//            nextTokensContext = null
//            nextTokensState = ATNState.INVALID_STATE_NUMBER
//            return
//        }
//
//        if (nextTokens.contains(Token.EPSILON)) {
//            if (nextTokensContext == null) {
//                // It's possible the next token won't match; information tracked
//                // by sync is restricted for performance.
//                nextTokensContext = recognizer.context
//                nextTokensState = recognizer.state
//            }
//            return
//        }
//
//        when (s.stateType) {
//            ATNState.BLOCK_START, ATNState.STAR_BLOCK_START, ATNState.PLUS_BLOCK_START, ATNState.STAR_LOOP_ENTRY -> {
//                // report error and recover if possible
//                if (singleTokenDeletion(recognizer) != null) {
//                    return
//                }
//
//                recover(recognizer, null)
////                throw InputMismatchException(recognizer)
//            }
//            ATNState.PLUS_LOOP_BACK, ATNState.STAR_LOOP_BACK -> {
//                //			System.err.println("at loop back: "+s.getClass().getSimpleName());
//                reportUnwantedToken(recognizer)
//                val expecting = recognizer.expectedTokens
//                val whatFollowsLoopIterationOrRule = expecting.or(getErrorRecoverySet(recognizer))
//                consumeUntil(recognizer, whatFollowsLoopIterationOrRule)
//            }
//        }
    }

    private fun consumeUntilResult(recognizer: Parser, set: IntervalSet): Int {

//		System.err.println("consumeUntil("+set.toString(recognizer.getTokenNames())+")");
        var ttype = recognizer.inputStream.LA(1)
        while (ttype != Token.EOF && !set.contains(ttype)) {
            println("consume during recover LA(1)="+recognizer.tokenNames[ttype]);
//			recognizer.getInputStream().consume();
            recognizer.consume()
            ttype = recognizer.inputStream.LA(1)
        }

        println("Found valid: ${recognizer.tokenNames[ttype]}")
        return ttype
    }
}