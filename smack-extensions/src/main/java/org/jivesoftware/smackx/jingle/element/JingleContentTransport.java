/**
 *
 * Copyright 2017 Florian Schmaus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jivesoftware.smackx.jingle.element;

import java.util.Collections;
import java.util.List;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

/**
 * A jingle transport extension.
 *
 */
public abstract class JingleContentTransport implements ExtensionElement {

    public static final String ELEMENT = "transport";

    protected final List<JingleContentTransportCandidate> candidates;
    protected final List<JingleContentTransportInfo> infos;

    protected JingleContentTransport(List<JingleContentTransportCandidate> candidates) {
        this(candidates, null);
    }

    protected JingleContentTransport(List<JingleContentTransportCandidate> candidates, List<JingleContentTransportInfo> infos) {
        if (candidates != null) {
            this.candidates = Collections.unmodifiableList(candidates);
        }
        else {
            this.candidates = Collections.emptyList();
        }

        if (infos != null) {
            this.infos = infos;
        } else {
            this.infos = Collections.emptyList();
        }
    }

    public List<JingleContentTransportCandidate> getCandidates() {
        return candidates;
    }

    public List<JingleContentTransportInfo> getInfos() {
        return infos;
    }

    @Override
    public String getElementName() {
        return ELEMENT;
    }

    protected void addExtraAttributes(XmlStringBuilder xml) {

    }

    @Override
    public final XmlStringBuilder toXML() {
        XmlStringBuilder xml = new XmlStringBuilder(this);
        addExtraAttributes(xml);

        if (candidates.isEmpty()) {
            xml.closeEmptyElement();

        } else {

            xml.rightAngleBracket();
            xml.append(candidates);
            xml.append(infos);
            xml.closeElement(this);
        }

        return xml;
    }

}
